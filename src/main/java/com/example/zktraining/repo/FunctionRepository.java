/**
 * @author  Vinhcv
 * @version 1.0
 * @since   2020-08-28
 */

package com.example.zktraining.repo;

import com.example.zktraining.model.Function;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FunctionRepository extends JpaRepository<Function, Integer> {
    List<Function> findAllByStatusAndParentIsNullOrderByOrdAsc(Boolean isActive);

    Function findByFunctionId(Integer id);
}
