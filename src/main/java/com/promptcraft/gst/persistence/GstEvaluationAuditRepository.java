package com.promptcraft.gst.persistence;

import com.promptcraft.gst.entity.GstEvaluationAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GstEvaluationAuditRepository
        extends JpaRepository<GstEvaluationAudit, Long> {
}
