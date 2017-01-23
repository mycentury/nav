/**
 * 
 */
package com.nav.service;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.nav.BaseTest;
import com.nav.entity.LinkEntity;

/**
 * @Desc
 * @author wewenge.yan
 * @Date 2017年1月23日
 * @ClassName DaoServiceTest
 */
public class DaoServiceTest extends BaseTest {

	@Autowired
	private DaoService daoService;

	/**
	 * Test method for {@link com.nav.service.DaoService#insert(com.nav.entity.BaseEntity)}.
	 */
	@Test
	public void testInsertT() {
	}

	/**
	 * Test method for {@link com.nav.service.DaoService#insert(java.util.List, java.lang.Class)}.
	 */
	@Test
	public void testInsertListOfTClassOfT() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.nav.service.DaoService#delete(com.nav.entity.BaseEntity)}.
	 */
	@Test
	public void testDeleteT() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.nav.service.DaoService#delete(com.nav.service.DaoService.Condition, java.lang.Class)}.
	 */
	@Test
	public void testDeleteConditionClassOfQ() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.nav.service.DaoService#save(com.nav.entity.BaseEntity)}.
	 */
	@Test
	public void testSaveT() {
		LinkEntity entity = new LinkEntity();
		entity.setPrimaryKey("G", "百度");
		entity.setImageUrl("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo_top_ca79a146.png");
		entity.setUrl("https://www.baidu.com/");
		daoService.save(entity);

		entity.setPrimaryKey("G", "搜狗");
		entity.setImageUrl(null);
		entity.setUrl("https://www.sogou.com/");
		daoService.save(entity);
	}

	/**
	 * Test method for {@link com.nav.service.DaoService#save(java.util.List)}.
	 */
	@Test
	public void testSaveListOfT() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.nav.service.DaoService#count(com.nav.service.DaoService.Condition, java.lang.Class)}.
	 */
	@Test
	public void testCount() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.nav.service.DaoService#exists(com.nav.service.DaoService.Condition, java.lang.Class)}.
	 */
	@Test
	public void testExists() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.nav.service.DaoService#existsById(java.lang.String, java.lang.Class)}.
	 */
	@Test
	public void testExistsById() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.nav.service.DaoService#queryById(java.lang.String, java.lang.Class)}.
	 */
	@Test
	public void testQueryById() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.nav.service.DaoService#query(com.nav.service.DaoService.Condition, java.lang.Class)}.
	 */
	@Test
	public void testQuery() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.nav.service.DaoService#getIdColName(java.lang.Class)}.
	 */
	@Test
	public void testGetIdColName() {
		fail("Not yet implemented");
	}

}
