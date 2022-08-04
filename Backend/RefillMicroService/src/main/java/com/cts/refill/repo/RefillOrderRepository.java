/**
 * 
 */
package com.cts.refill.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.refill.model.RefillOrder;


@Repository
public interface RefillOrderRepository extends JpaRepository<RefillOrder, Long>{

}
