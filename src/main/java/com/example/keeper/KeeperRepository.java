package com.example.keeper;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface KeeperRepository extends CrudRepository<Keeper, Long> {

    Optional<Keeper> findBySpace(String space);
}
