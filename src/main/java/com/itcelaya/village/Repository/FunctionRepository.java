package com.itcelaya.village.Repository;

import com.itcelaya.village.Model.Function;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FunctionRepository extends JpaRepository<Function, Long> {
}
