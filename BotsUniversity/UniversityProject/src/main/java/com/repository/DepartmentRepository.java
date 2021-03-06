package com.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.entity.Department;
import com.entity.Lector;

public class DepartmentRepository {
final EntityManager manager;
	
	public DepartmentRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void save(Department department) {
		manager.getTransaction().begin();
		manager.persist(department);
		manager.getTransaction().commit();
	}
	
	public List<Department> findAll(){
		List<Department> departments = manager.createQuery("from Department").getResultList();
		return departments;
	}
	
	public Department findOne(int id) {
		Department department = (Department)manager.createQuery("select c from Department c where c.id=:idDepartment").setParameter("idDepartment", id).getSingleResult();
		return department;
	}
	
	public void remove(int id) {
		manager.remove(findOne(id));
	}
	
	public Department findByName(String name) {
		Department department = (Department)manager.createQuery("select d from Department d where d.name like :nameDepartment").setParameter("nameDepartment", name).getSingleResult();
		return department;
	}
}
