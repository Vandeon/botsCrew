package com.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Lector {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private int age;

	private int salary;
	
	@Enumerated(EnumType.STRING)
	private Degree degree;
	
	@ManyToMany
	@JoinTable(name = "lector_department", 
				joinColumns = @JoinColumn(name = "id_lector"),
				inverseJoinColumns = @JoinColumn(name = "id_department"))
	private List<Department> departments = new ArrayList<Department>();

	@OneToMany(mappedBy="head")
	private List<Department> departmentsHeaded = new ArrayList<Department>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Degree getDegree() {
		return degree;
	}

	public void setDegree(Degree degree) {
		this.degree = degree;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public List<Department> getDepartmentsHeaded() {
		return departmentsHeaded;
	}

	public void setDepartmentsHeaded(List<Department> departmentsHeaded) {
		this.departmentsHeaded = departmentsHeaded;
	}

	@Override
	public String toString() {
		return "Lector "+ name + ", " + age + " y.o. Salary:"
				+ salary + ", degree - " + degree + ".";
	}
	
}
