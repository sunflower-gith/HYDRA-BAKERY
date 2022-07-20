package edu.poly.assigmentsof3021.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import edu.poly.assigmentsof3021.domain.OrderDetail;
import edu.poly.assigmentsof3021.reponsitory.OrderDetailReponsitory;
import edu.poly.assigmentsof3021.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{
@Autowired
OrderDetailReponsitory orderDetailReponsitory;

@Override
public <S extends OrderDetail> S save(S entity) {
	return orderDetailReponsitory.save(entity);
}

@Override
public <S extends OrderDetail> Optional<S> findOne(Example<S> example) {
	return orderDetailReponsitory.findOne(example);
}

@Override
public List<OrderDetail> findAll() {
	return orderDetailReponsitory.findAll();
}

@Override
public Page<OrderDetail> findAll(Pageable pageable) {
	return orderDetailReponsitory.findAll(pageable);
}

@Override
public List<OrderDetail> findAll(Sort sort) {
	return orderDetailReponsitory.findAll(sort);
}

@Override
public List<OrderDetail> findAllById(Iterable<Long> ids) {
	return orderDetailReponsitory.findAllById(ids);
}

@Override
public Optional<OrderDetail> findById(Long id) {
	return orderDetailReponsitory.findById(id);
}

@Override
public <S extends OrderDetail> List<S> saveAll(Iterable<S> entities) {
	return orderDetailReponsitory.saveAll(entities);
}

@Override
public void flush() {
	orderDetailReponsitory.flush();
}

@Override
public boolean existsById(Long id) {
	return orderDetailReponsitory.existsById(id);
}

@Override
public <S extends OrderDetail> S saveAndFlush(S entity) {
	return orderDetailReponsitory.saveAndFlush(entity);
}

@Override
public <S extends OrderDetail> List<S> saveAllAndFlush(Iterable<S> entities) {
	return orderDetailReponsitory.saveAllAndFlush(entities);
}

@Override
public <S extends OrderDetail> Page<S> findAll(Example<S> example, Pageable pageable) {
	return orderDetailReponsitory.findAll(example, pageable);
}

@Override
public void deleteInBatch(Iterable<OrderDetail> entities) {
	orderDetailReponsitory.deleteInBatch(entities);
}

@Override
public <S extends OrderDetail> long count(Example<S> example) {
	return orderDetailReponsitory.count(example);
}

@Override
public void deleteAllInBatch(Iterable<OrderDetail> entities) {
	orderDetailReponsitory.deleteAllInBatch(entities);
}

@Override
public long count() {
	return orderDetailReponsitory.count();
}

@Override
public <S extends OrderDetail> boolean exists(Example<S> example) {
	return orderDetailReponsitory.exists(example);
}

@Override
public void deleteById(Long id) {
	orderDetailReponsitory.deleteById(id);
}

@Override
public void deleteAllByIdInBatch(Iterable<Long> ids) {
	orderDetailReponsitory.deleteAllByIdInBatch(ids);
}

@Override
public void delete(OrderDetail entity) {
	orderDetailReponsitory.delete(entity);
}

@Override
public <S extends OrderDetail, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
	return orderDetailReponsitory.findBy(example, queryFunction);
}

@Override
public void deleteAllById(Iterable<? extends Long> ids) {
	orderDetailReponsitory.deleteAllById(ids);
}

@Override
public void deleteAllInBatch() {
	orderDetailReponsitory.deleteAllInBatch();
}

@Override
public OrderDetail getOne(Long id) {
	return orderDetailReponsitory.getOne(id);
}

@Override
public void deleteAll(Iterable<? extends OrderDetail> entities) {
	orderDetailReponsitory.deleteAll(entities);
}

@Override
public void deleteAll() {
	orderDetailReponsitory.deleteAll();
}

@Override
public OrderDetail getById(Long id) {
	return orderDetailReponsitory.getById(id);
}

@Override
public OrderDetail getReferenceById(Long id) {
	return orderDetailReponsitory.getReferenceById(id);
}

@Override
public <S extends OrderDetail> List<S> findAll(Example<S> example) {
	return orderDetailReponsitory.findAll(example);
}

@Override
public <S extends OrderDetail> List<S> findAll(Example<S> example, Sort sort) {
	return orderDetailReponsitory.findAll(example, sort);
}

@Override
public Page<OrderDetail> findByOrderId(Long orderId, Pageable pageable) {
	return orderDetailReponsitory.findByOrderId(orderId, pageable);
}

}
