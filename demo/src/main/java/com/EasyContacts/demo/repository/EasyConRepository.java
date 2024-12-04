package com.EasyContacts.demo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EasyContacts.demo.model.EasyConModel;

@Repository
public interface EasyConRepository extends JpaRepository<EasyConModel, UUID> {
}
