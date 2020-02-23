package org.myproject.hibernate;

import java.text.DateFormatSymbols;
import java.time.Month;
import java.util.*;
import java.util.TreeSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.myproject.hibernate.entity.Transaction;

public class App {
	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(Transaction.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();
		try {
			Set<Integer> customerSet = new TreeSet<>();
			Transaction trans = new Transaction();
			session.beginTransaction();

			List<Transaction> translist = session.createQuery("from transaction").list();
			for (Transaction transaction : translist) {
				int id = transaction.getcID();
				customerSet.add(id);

			}
			for (Integer am : customerSet) {

				Set<Integer> monthset = new TreeSet<>();
				List<Transaction> custTrans = session.createQuery("from transaction where cID = " + am).list();
				for (Transaction transt : custTrans) {
					Date d = (Date) transt.getTdate();

					Calendar cal = Calendar.getInstance();
					cal.setTime(d);
					int months = cal.get(Calendar.MONTH);

					monthset.add(months);

				}
				int total = 0;
				for (int eachMonth : monthset) {

					int bonusPoint = 0;
					// System.out.println(am +" "+eachMonth);
					List<Transaction> listbymonth = session.createQuery(
							"from transaction where cID = " + am + "AND extract(month from tdate)=" + (eachMonth + 1))
							.list();
					// System.out.println("List for cId "+am+" "+"where month="+(eachMonth+1)+"
					// "+listbymonth);
					for (Transaction finTrans : listbymonth) {

						int priceCompare = Integer.parseInt(finTrans.getPrice());
						if (priceCompare > 100) {
							bonusPoint = bonusPoint + 2 * (priceCompare - 100) + 1 * (priceCompare - 50)
									- (priceCompare - 100);
						} else if (priceCompare > 50) {
							bonusPoint = bonusPoint + 1 * (priceCompare - 50);
						} else {
							bonusPoint = 0;
						}

					}
					String m = new DateFormatSymbols().getMonths()[eachMonth];
					System.out.println(
							"Reward Point earned by Customer " + am + " per Month  " + m + " is: " + bonusPoint);
					total = total + bonusPoint;
				}
				System.out.println("Total Reward Point earned by Customer " + am + " is: " + total);

			}

		} finally {
			session.close();
			factory.close();
		}
	}
}
