package com.citi.tradeservices.dao.tlmonitor;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.citi.tradeservices.domain.tlmonitor.Message;
import com.citi.tradeservices.domain.tlmonitor.MessageDetails;


@Repository
public class MessageDAOImpl implements MessageDAO {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public MessageDAOImpl() { 
		
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<MessageDetails> getMessageDetails(String requestId) {
		
		DetachedCriteria criteria = DetachedCriteria.forClass(MessageDetails.class);
		criteria.add(Restrictions.or(				
				Restrictions.like("requestId", "%"+requestId+"%"),
				Restrictions.like("messageContent", "%"+requestId+"%")
				));
	
		return (List<MessageDetails>) getHibernateTemplate().findByCriteria(criteria);
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Message> getMessages(Boolean isError, Date from, Date to) { 
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Message.class)				
				.addOrder(Order.desc("messageDate"));
				
		if (isError != null) { 
			if (isError) { 
				criteria.add(Restrictions.not(Restrictions.in("messageCode", new Integer[]{100})));
			} else {
				criteria.add(Restrictions.in("messageCode", new Integer[]{100}));
			}
		}
				
		if (from != null) { 
			if (to != null) { 
				criteria.add(Restrictions.between("messageDate", getLower(from), getHigher(to)));	
			} else { 
				criteria.add(Restrictions.ge("messageDate", getLower(from)));
			}		
		} else if (to != null) {
			criteria.add(Restrictions.le("messageDate", getHigher(to)));			
		} else { 			
			criteria.add(Restrictions.between("messageDate", getLower(), getHigher()));			
		}
		
		
		
				
		return (List<Message>) getHibernateTemplate().findByCriteria(criteria);
		
		
	}
	
	private Date getLower() { 
		return getLower(new Date());
	}
	
	private Date getLower(Date date) { 
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 1);
		return c.getTime();
	}

	private Date getHigher() { 
		return getHigher(new Date());
	}
	
	private Date getHigher(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 1);
		return c.getTime();		
	}
	
	private HibernateTemplate getHibernateTemplate() { 
		return hibernateTemplate;
	}




	
}
