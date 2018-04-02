package com.example.commerce.catalog.core.infrastructure.db;

import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SequenceGenerator {
	
	private final MongoOperations mongo;

	public long getNextSequence(String seqName) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(seqName));
		CustomSequence counter = mongo.findAndModify(query, new Update().inc("seq", 1), FindAndModifyOptions.options().returnNew(true).upsert(true), CustomSequence.class);
		return counter.getSeq();
	}

}
