package edu.poly.assigmentsof3021.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.poly.assigmentsof3021.domain.Category;

public interface CategoryService {

	Category getReferenceById(Long id);

	Category getById(Long id);

	void deleteAll();

	void deleteAll(Iterable<? extends Category> entities);

	Category getOne(Long id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Long> ids);

	void delete(Category entity);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteById(Long id);

	long count();

	void deleteAllInBatch(Iterable<Category> entities);

	void deleteInBatch(Iterable<Category> entities);

	<S extends Category> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Category> S saveAndFlush(S entity);

	boolean existsById(Long id);

	void flush();

	<S extends Category> List<S> saveAll(Iterable<S> entities);

	Optional<Category> findById(Long id);

	List<Category> findAllById(Iterable<Long> ids);

	List<Category> findAll(Sort sort);

	Page<Category> findAll(Pageable pageable);

	List<Category> findAll();

	<S extends Category> S save(S entity);

	Page<Category> findByNameContaining(String name, Pageable pageable);

	Page<Category> findAllById(Long categoryId, Pageable pageable);


}
