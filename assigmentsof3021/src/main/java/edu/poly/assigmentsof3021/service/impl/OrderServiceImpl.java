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

import edu.poly.assigmentsof3021.domain.Order;
import edu.poly.assigmentsof3021.reponsitory.OrderReponsitory;
import edu.poly.assigmentsof3021.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
@Autowired
OrderReponsitory orderReponsitory;

@Override
public <S extends Order> S save(S entity) {
	return orderReponsitory.save(entity);
}

@Override
public <S extends Order> Optional<S> findOne(Example<S> example) {
	return orderReponsitory.findOne(example);
}

@Override
public List<Order> findAll() {
	return orderReponsitory.findAll();
}

@Override
public Page<Order> findAll(Pageable pageable) {
	return orderReponsitory.findAll(pageable);
}

@Override
public List<Order> findAll(Sort sort) {
	return orderReponsitory.findAll(sort);
}

@Override
public List<Order> findAllById(Iterable<Long> ids) {
	return orderReponsitory.findAllById(ids);
}

@Override
public Optional<Order> findById(Long id) {
	return orderReponsitory.findById(id);
}

@Override
public <S extends Order> List<S> saveAll(Iterable<S> entities) {
	return orderReponsitory.saveAll(entities);
}

@Override
public void flush() {
	orderReponsitory.flush();
}

@Override
public boolean existsById(Long id) {
	return orderReponsitory.existsById(id);
}

@Override
public <S extends Order> S saveAndFlush(S entity) {
	return orderReponsitory.saveAndFlush(entity);
}

@Override
public <S extends Order> List<S> saveAllAndFlush(Iterable<S> entities) {
	return orderReponsitory.saveAllAndFlush(entities);
}

@Override
public <S extends Order> Page<S> findAll(Example<S> example, Pageable pageable) {
	return orderReponsitory.findAll(example, pageable);
}

@Override
public void deleteInBatch(Iterable<Order> entities) {
	orderReponsitory.deleteInBatch(entities);
}

@Override
public <S extends Order> long count(Example<S> example) {
	return orderReponsitory.count(example);
}

@Override
public void deleteAllInBatch(Iterable<Order> entities) {
	orderReponsitory.deleteAllInBatch(entities);
}

@Override
public long count() {
	return orderReponsitory.count();
}

@Override
public <S extends Order> boolean exists(Example<S> example) {
	return orderReponsitory.exists(example);
}

@Override
public void deleteById(Long id) {
	orderReponsitory.deleteById(id);
}

@Override
public void deleteAllByIdInBatch(Iterable<Long> ids) {
	orderReponsitory.deleteAllByIdInBatch(ids);
}

@Override
public void delete(Order entity) {
	orderReponsitory.delete(entity);
}

@Override
public <S extends Order, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
	return orderReponsitory.findBy(example, queryFunction);
}

@Override
public void deleteAllById(Iterable<? extends Long> ids) {
	orderReponsitory.deleteAllById(ids);
}

@Override
public void deleteAllInBatch() {
	orderReponsitory.deleteAllInBatch();
}

@Override
public Order getOne(Long id) {
	return orderReponsitory.getOne(id);
}

@Override
public void deleteAll(Iterable<? extends Order> entities) {
	orderReponsitory.deleteAll(entities);
}

@Override
public void deleteAll() {
	orderReponsitory.deleteAll();
}

@Override
public Order getById(Long id) {
	return orderReponsitory.getById(id);
}

@Override
public Order getReferenceById(Long id) {
	return orderReponsitory.getReferenceById(id);
}

@Override
public <S extends Order> List<S> findAll(Example<S> example) {
	return orderReponsitory.findAll(example);
}

@Override
public <S extends Order> List<S> findAll(Example<S> example, Sort sort) {
	return orderReponsitory.findAll(example, sort);
}

@Override
public Page<Order> findByCustomerId(Long customerId, Pageable pageable) {
	return orderReponsitory.findByCustomerId(customerId, pageable);
}

}
