package com.promptcraft.gst.persistence;

import com.promptcraft.gst.entity.GstEvaluationRun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GstEvaluationRunRepository
        extends JpaRepository<GstEvaluationRun, Long> {
}
