package com.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.entity.Degree;
import com.entity.Department;
import com.entity.Lector;
import com.repository.DepartmentRepository;
import com.repository.LectorRepository;

public class Main {

	//�������� �������, ���� �� � ��� �'���� ������� �������
	
	public static void main(String[] args) {		
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("primary");
		EntityManager manager = factory.createEntityManager();
		LectorRepository lr = new LectorRepository(manager);
		DepartmentRepository dr = new DepartmentRepository(manager);

		System.out.println("Wellcome to our university!");

		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println();
			System.out.println("Make your choise:" + "\n 1 - Generate DB with departments and lectors."
					+ "\n 2 - Find a head of department." + "\n 3 - Show department statistic"
					+ "\n 4 - Show the avarage salary for department." + "\n 5 - Show count of employee for department."
					+ "\n 6 - Global search." + "\n 7 - Manage DB." + "\n 8 - Exit.");
			System.out.println();
			int key = sc.nextInt();
			switch (key) {
			case 1:
				generate(lr, dr);
				break;
			case 2:
				System.out.println("Enter name of department:");
				sc.nextLine();
				String dep = sc.nextLine();
				System.out.println(findHead(dep, dr));
				break;
			case 3:
				System.out.println("Enter name of department:");
				sc.nextLine();
				String department = sc.nextLine();
				statistic(department, dr);
				break;
			case 4:
				System.out.println("Enter name of department:");
				sc.nextLine();
				String departmentName = sc.nextLine();
				System.out.println("The average salary of" + departmentName + "is " + avgSalary(departmentName, dr));
				break;
			case 5:
				System.out.println("Enter name of department:");
				sc.nextLine();
				String departName = sc.nextLine();
				System.out.println(eCount(departName, dr) + " employees");
				break;
			case 6:
				System.out.println("Enter some string:");
				sc.nextLine();
				String search = sc.nextLine();
				List<Lector> list = lr.globalSerach(search);
				for (int i = 0; i < list.size(); i++) {
					System.out.println(list.get(i).getName());

				}
				break;
			case 7:
				System.out.println("What would u like to change?" + "\n 1 - Add department."
						+ "\n 2 - Remove department." + "\n 3 - Add lector." + "\n 4 - Remove lector."
						+ "\n 5 - Add lector to department." + "\n 6 - Back.");
				int secondKey = sc.nextInt();
				switch (secondKey) {
				case 1:
					sc.nextLine();
					String name = sc.nextLine();
					addDep(dr, name);
					System.out.println("Added.");
					break;
				case 2:
					sc.nextLine();
					String named = sc.nextLine();
					dr.remove(dr.findByName(named).getId());
					System.out.println("Removed.");
					break;
				case 3:
					sc.nextLine();
					System.out.println("Enter firstname and lastname:");
					String nameL = sc.nextLine();
					System.out.println("Enter degree (assistant, associate_professor, professor):");
					String degree = sc.nextLine();
					System.out.println("Enter age:");
					int age = sc.nextInt();
					System.out.println("Enter salary:");
					int salary = sc.nextInt();
					addLector(lr, nameL, degree, age, salary);
					System.out.println("Added.");
					break;
				case 4:
					sc.nextLine();
					String namel = sc.nextLine();
					lr.remove(lr.findByName(namel).getId());
					System.out.println("Removed.");
					break;
				case 5:
					sc.nextLine();
					System.out.println("Enter department:");
					String depn = sc.nextLine();
					System.out.println("Enter lector:");
					String lecn = sc.nextLine();
					Department dp = dr.findByName(depn);
					dp.getLectors().add(lr.findByName(lecn));
					dr.save(dp);
					break;
				default:
					break;
				}
				break;
			case 8:
				System.out.println("Good luck!");
				System.exit(0);
				break;

			}
		}

	}

	public static String findHead(String depName, DepartmentRepository dr) {
		Lector head = (Lector) dr.findByName(depName).getHead();
		return "Head of " + depName + " is " + head.getName();
	}

	public static void statistic(String depName, DepartmentRepository dr) {
		List<Lector> lectors = dr.findByName(depName).getLectors();
		int p = 0;
		int a = 0;
		int ap = 0;
		for (int i = 0; i < lectors.size(); i++) {
			if (lectors.get(i).getDegree() == Degree.PROFESSOR) {
				p++;
			} else if (lectors.get(i).getDegree() == Degree.ASSISTANT) {
				a++;
			} else {
				ap++;
			}
		}
		System.out.println(" assistans - " + a + "\n associate professors - " + ap + "\n professors - " + p);
	}

	public static int avgSalary(String depName, DepartmentRepository dr) {
		List<Lector> lectors = dr.findByName(depName).getLectors();
		int avg = 0;
		for (int i = 0; i < lectors.size(); i++) {
			avg += lectors.get(i).getSalary();
		}
		return avg / lectors.size();
	}

	public static int eCount(String depName, DepartmentRepository dr) {
		return dr.findByName(depName).getLectors().size();
	}

	public static void addDep(DepartmentRepository dr, String name) {
		Department dep = new Department();
		dep.setName(name);
		dr.save(dep);
	}
	
	public static void addLector(LectorRepository lr, String name, String degree, int age, int salary) {
		Lector lector = new Lector();
		lector.setName(name);		
		lector.setDegree(Degree.valueOf(degree.toUpperCase()));
		lector.setAge(age);
		lector.setSalary(salary);
		lr.save(lector);
	}

	public static void generate(LectorRepository lr, DepartmentRepository dr) {
		
		String[] depNames = {"Department of Architecture", "Department of Italian", "Department of Middle Eastern Studies", "Department of Archaeology", "Department of Sociology"};
		for(int i = 0; i < 5; i++) {
			addDep(dr, depNames[i]);
		}
		
		String lectors[] = {"Jakob White", "PROFESSOR", "John Black", "PROFESSOR", "Mike Tysson", "PROFESSOR", "Petro Poroshenko", "ASSOCIATE_PROFESSOR", "John Snow", "ASSOCIATE_PROFESSOR", "Alexand Makedonskij", 
				"ASSOCIATE_PROFESSOR","Karim Benzema", "ASSISTANT","Lionell Messi", "ASSISTANT","Eduard Petrenko", "ASSISTANT","Donald Trump", "ASSISTANT"};
		for(int i =0; i < 10; i++) {
			int age = (int)(Math.random()*50 + 20);
			int salary = (int)(Math.random()*5000 + 5000);
			addLector(lr, lectors[i*2], lectors[i*2+1], age, salary);
		}
		
		for(int i = 0; i < 5; i++) {
			Department department = dr.findByName(depNames[i]);
			department.setHead(lr.findByName(lectors[i*2]));
			List<Lector> list = new ArrayList<Lector>();
			list.add(lr.findByName(lectors[i*2]));
			list.add(lr.findByName(lectors[i*2+10]));
			department.setLectors(list);
			dr.save(department);
			
		}


	}

}
