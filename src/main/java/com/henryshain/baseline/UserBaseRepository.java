package com.henryshain.baseline;

import org.springframework.data.repository.CrudRepository;


public interface UserBaseRepository extends CrudRepository<UserBase, Integer> {

	public UserBase findFirstById(int id);

	public UserBase findByEmail(String email);

	public UserBase findByName(String name);

	public int countByName(String name);

	public int countByEmail(String email);

}
