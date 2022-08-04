/**
 * 
 */
package com.cts.refill.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cts.refill.model.RefillOrderLine;


@Repository
@Transactional
public interface RefillOrderLineRepository extends JpaRepository<RefillOrderLine, Long> {
	
	@Modifying
	@Query("delete from refillOrder_line where subID=?1")
	public int deleteBySubscriptionID(long subID);
}
