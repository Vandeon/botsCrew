package com.entity;

import java.awt.FontFormatException;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import com.repository.LectorRepository;

public class Test {

	public static void main(String[] args) {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("primary");
		EntityManager manager = factory.createEntityManager();
		
		LectorRepository lectorRepository = new LectorRepository(manager);
		Lector lector1 = new Lector();
		lector1.setName("Jakob Whitekub");
		lector1.setDegree(Degree.PROFESSOR);
		lectorRepository.save(lector1);
		
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
//		String str = new String("%kub%");
//		List<Lector> list = (List<Lector>)manager.createQuery("select l from Lector l where l.firstName like :str").setParameter("str", str).getResultList();
		
		List<Lector> list = lectorRepository.globalSerach(str);
		
		for(int i = 0; i < list.size(); i++)
		System.out.println(list.get(i));

	}

}
