package fr.iut.sj.pkdxapi.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import fr.iut.sj.pkdxapi.models.PkmnData;

public interface PkmnRepository extends MongoRepository<PkmnData, ObjectId>{
    @Query("{'name': ?0}")
    List<PkmnData> findByName(String name);

    @Query("{'name': {$regex: ?0, $options: 'i'}}")
    List<PkmnData> findByPartialName(String partialName);
}