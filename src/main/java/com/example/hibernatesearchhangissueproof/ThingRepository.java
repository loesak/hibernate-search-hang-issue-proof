package com.example.hibernatesearchhangissueproof;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ThingRepository extends PagingAndSortingRepository<Thing, UUID> {



}
