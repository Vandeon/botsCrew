package com.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.entity.Department;
import com.entity.Lector;

public class LectorRepository {

	final EntityManager manager;
	
	public LectorRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void save(Lector lector) {
		manager.getTransaction().begin();
		manager.persist(lector);
		manager.getTransaction().commit();
	}
	
	public List<Lector> findAll(){
		List<Lector> lectors = manager.createQuery("from Lector").getResultList();
		return lectors;
	}
	
	public Lector findOne(int id) {
		Lector lector = (Lector)manager.createQuery("select c from Lector c where c.id=:idLector").setParameter("idLector", id).getSingleResult();
		return lector;
	}
	
	public void remove(int id) {
		manager.remove(findOne(id));
	}
	
	public List<Lector> globalSerach(String str){
		str = "%" + str + "%";
		List<Lector> list = (List<Lector>)manager.createQuery("select l from Lector l where l.name like :str").setParameter("str", str).getResultList();
		return list;
	}
	
	public Lector findByName(String name) {
		Lector lector = (Lector)manager.createQuery("select l from Lector l where l.name like :nameLector").setParameter("nameLector", name).getSingleResult();
		return lector;
	}
	
}
