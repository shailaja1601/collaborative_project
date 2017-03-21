package com.niit.collabaration.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collabaration.dao.JobDAO;
import com.niit.collabaration.model.Job;
import com.niit.collabaration.model.JobApplication;


@Repository("jobDAO")
public class JobDAOImpl implements JobDAO {

	private static final Logger log = LoggerFactory.getLogger(JobDAOImpl.class);

	@Autowired(required = true)
	private SessionFactory sessionFactory;

	public JobDAOImpl(SessionFactory sessionFactory) {

		try {
			this.sessionFactory = sessionFactory;
		} catch (Exception e) {
			log.error("Unable to connect with to db");
			e.printStackTrace();
		}
	}

	@Transactional
	public List<Job> getAllOpenedJobs() {
		
		log.debug("Startiong of the method getAllOpenedJobs");
		String hql = "from Job where status ='" + "V'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
		
	}

	@Transactional
	public Job getJobDetails(Long jobID) {
		log.debug("Startiong of the method getJobDetails");
		
		Job job = (Job) sessionFactory.getCurrentSession().get(Job.class, jobID);

		return job;
	}

	@Transactional
	public JobApplication getJobApplication(String userId, Long jobID) {
		log.debug("Startoing of the method getJobApplication");

		String hql = "from JobApplication where userId ='" + userId +  "'  and jobID ='" + jobID + "'";
		log.debug("hql" +hql);
		return (JobApplication) sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
	}

	/*@Transactional
	public JobApplication getJobApplication(Long jobID) {
		log.debug("Starting of the method getJobApplication");
		return (JobApplication) sessionFactory.getCurrentSession().get(JobApplication.class, jobID);
	}*/

	@Transactional
	public boolean updateJob(Job job) {
		log.debug("Starting of the method updateJob");
		try {
			sessionFactory.getCurrentSession().update(job);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Transactional
	public boolean updateJobApplication(JobApplication jobApplication) {
		log.debug("Startiong of the method updateJobApplication");
		try {
			sessionFactory.getCurrentSession().update(jobApplication);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}


	@Transactional
	public boolean save(JobApplication jobApplied) {
		log.debug("Startiong of the method save JobApplication");
		try {
			//jobApplied.ge;
			sessionFactory.getCurrentSession().save(jobApplied);
		} catch (HibernateException e) {
			
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Transactional
	public boolean save(Job job) {
		log.debug("Starting of the method save Job ");
		try {
			sessionFactory.getCurrentSession().save(job);
		} catch (HibernateException e) {
			
			e.printStackTrace();
			return false;
		}
		return true;
	}
	

	@Transactional
	public List<Job> getMyAppliedJobs(String userId) {
		log.debug("Startiong of the method getMyAppliedJobs");
		System.out.println(userId);
		String hql = "from JobApplication where userId = '" + userId +  "'";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}



}