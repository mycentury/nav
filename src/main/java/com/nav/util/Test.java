/**
 * 
 */
package com.nav.util;

import java.sql.Date;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Desc
 * @author wewenge.yan
 * @Date 2017年2月8日
 * @ClassName Test
 */
public class Test {
	private static boolean flag = true;

	public static void main(String[] args) {
		System.out.println(new Date(1487520000000L));
		CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
		ArrayList<String> arr = new ArrayList<>();
		// for (int i = 0; i < 1000; i++) {
		test();
		// }
	}

	/**
	 * 
	 */
	private static void test() {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				int i = 0;
				while (flag) {
					i++;
				}
				System.out.println(i);
			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				flag = false;
			}
		});

		t1.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t2.start();
	}
}
