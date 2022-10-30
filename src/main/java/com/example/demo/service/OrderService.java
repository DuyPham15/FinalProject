package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Customer;
import com.example.demo.entities.Food;
import com.example.demo.entities.Order;
import com.example.demo.entities.OrderDetail;
import com.example.demo.entities.OrderStatus;
import com.example.demo.entities.User;
import com.example.demo.model.CartInfo;
import com.example.demo.model.CartLineInfo;
import com.example.demo.model.CustomerInfo;
import com.example.demo.model.OrderSummary;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.FoodRepository;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private FoodRepository foodRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@Autowired
	private FoodService foodService;

	
	public String addOrder(CartInfo cartInfo) {
		// int orderNum = this.getMaxOrderNum() + 1;
		Order order = new Order();
		Customer customer = new Customer();
		CustomerInfo customerInfo = cartInfo.getCustomerInfo();
		customer.setFirstName(customerInfo.getFirstName());
		customer.setLastName(customerInfo.getLastName());
		customer.setEmail(customerInfo.getEmail());
		customer.setPhoneNumber(customerInfo.getNumberPhone());
		// chuyen het column ben address entity qua Customer entity
		customer.setAddress(customerInfo.getAddress());
		customer.setWard(customerInfo.getWard());
		customer.setDistrict(customerInfo.getDistrict());
		customer.setCity(customerInfo.getCity());
		if (customerInfo.getFirstName() == null || customerInfo.getFirstName().isEmpty()) {
			return "Vui lòng nhập Tên!";
		}
		if (customerInfo.getLastName() == null || customerInfo.getLastName().isEmpty()) {
			return "Vui lòng nhập Họ!";
		}
		if (customerInfo.getAddress() == null || customerInfo.getAddress().isEmpty()) {
			return "Vui lòng nhập Số nhà, tên đường!";
		}
		if (customerInfo.getWard() == null || customerInfo.getWard().isEmpty()) {
			return "Vui lòng nhập Phường, xã!";
		}
		if (customerInfo.getDistrict() == null || customerInfo.getDistrict().isEmpty()) {
			return "Vui lòng nhập Quận, huyện!";
		}
		if (customerInfo.getCity() == null || customerInfo.getCity().isEmpty()) {
			return "Vui lòng nhập Thành phố!";
		}
		if (customerInfo.getEmail() == null || customerInfo.getEmail().isEmpty()) {
			return "Vui lòng nhập Email!";
		}
		if (customerInfo.getNumberPhone() == null || customerInfo.getNumberPhone().isEmpty()) {
			return "Vui lòng nhập số điện thoại!";
		}
		customerRepository.save(customer);
		java.util.Date today = new java.util.Date();
		order.setDate(new java.sql.Date(today.getTime()));
		order.setTotalPrice(cartInfo.getAmountTotal());
		order.setTotalQuantity(cartInfo.getQuantityTotal());
		order.setCustomer(customer);
		order.setStatus(OrderStatus.RECEIVED);
		// TODO Auto-generated method stub
		orderRepository.save(order);
		List<CartLineInfo> lines = cartInfo.getCartLines();
		for (CartLineInfo line : lines) {
			OrderDetail detail = new OrderDetail();
			detail.setOrder(order);
			detail.setAmount(line.getAmount());
			detail.setPrice(line.getProductInfo().getProductPrice());
			detail.setQuantity(line.getQuantity());
			Long code = line.getProductInfo().getProductId();
			Food food = this.foodService.getFoodById(code);			
			detail.setFood(food);
			orderDetailRepository.save(detail);
			
			Food currentFood = foodService.getFoodById(code);
			currentFood.setQuantity(currentFood.getQuantity()-line.getQuantity());
			foodRepository.save(currentFood);
		}
		
		// Set OrderNum for report.
		// Set OrderNum để thông báo cho người dùng.
		cartInfo.setOrderNum(order.getId());
		return null;
	}
	
	public Order receivedOrder(Long id) {
		Order currentOrder = findOrderById(id);
		currentOrder.setStatus(OrderStatus.RECEIVED);
		return orderRepository.save(currentOrder);
	}
	
	public Order packedOrder(Long id) {
		Order currentOrder = findOrderById(id);
		currentOrder.setStatus(OrderStatus.PACKAGED);
		return orderRepository.save(currentOrder);
	}
	
	public Order deliveriedOrder(Long id) {
		Order currentOrder = findOrderById(id);
		currentOrder.setStatus(OrderStatus.DELIVERIED);
		return orderRepository.save(currentOrder);
	}
	
	public Order canceledOrder(Long id) {
		Order currentOrder = findOrderById(id);
		currentOrder.setStatus(OrderStatus.CANCELED);
		return orderRepository.save(currentOrder);
	}
	
	public Order returnedOrder(Long id) {
		Order currentOrder = findOrderById(id);
		currentOrder.setStatus(OrderStatus.RETURNED);
		return orderRepository.save(currentOrder);
	}
	
	public Order finishedOrder(Long id) {
		Order currentOrder = findOrderById(id);
		currentOrder.setStatus(OrderStatus.FINISHED);
		return orderRepository.save(currentOrder);
	}
	
	public List<Order> getOrders() {
		return this.orderRepository.findAll();
	}
	
	public Order findOrderById(Long id) {
		Optional<Order> optionalOrder = orderRepository.findById(id);
		return optionalOrder.get();
	}
	
	public Page<Order> findAll(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		Page<Order> pageOrder = orderRepository.findAll(pageable);
		return pageOrder;
	}
	
	
	public OrderSummary getOrderSummary() {
		List<Order> orders = this.orderRepository.findAll();
		long orderReceivedCount = getOrderReceivedCount(orders);
		OrderSummary orderSummary = new OrderSummary();
		orderSummary.setOrderReceivedCount(orderReceivedCount);
		
		return orderSummary;
	}
	
	public Long getOrderReceivedCount(List<Order> orders) {
		long count=0;		
		for (Order order : orders) {
			if (order.getStatus().equals("RECEIVED") ) {
				count++;
			}
		}
		return count;
	}
	
	public long getOrderDeliveriedCount() {
		long count=0;
		List<Order> orders = this.orderRepository.findAll();
		for (Order order : orders) {
			if (order.getStatus().equals(3)) {
				count++;
			}
		}
		return count;
	}
	
	public long getOrderCanceledCount() {
		long count=0;
		List<Order> orders = this.orderRepository.findAll();
		for (Order order : orders) {
			if (order.getStatus().equals(4)) {
				count++;
			}
		}
		return count;
	}
	
	public long getOrderFinishedCount() {
		long count=0;
		List<Order> orders = this.orderRepository.findAll();
		for (Order order : orders) {
			if (order.getStatus().equals(6)) {
				count++;
			}
		}
		return count;
	}
	
//	public OrderSummary getSummary() {
//		OrderSummary orderSummary =  new OrderSummary();
//		long orderReceivedCount = orderSummary.getOrderReceivedCount();
//		long orderDeliveriedCount = orderSummary.getOrderDeliveriedCount();
//		long orderCanceledCount = orderSummary.getOrderCanceledCount();
//		long orderFinishedCount = orderSummary.getOrderFinishedCount();
//		
//	}
	
}
