/*
 *     Copyright 2022-2022 Yauhen Bichel yb3129h@gre.ac.uk OR bichel.eugen@gmail.com 
 *     Student Id 001185491
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.holidaysystem.repository;

import java.util.UUID;

import com.holidaysystem.entity.AccountEntity;

/**
 * Interface for Account repository, which can be supported by
 * all implementations of the interface
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
public interface IAccountRepository {
	/**
	 * Finds account by account id
	 * @param accountId UUID account id
	 * @return account entity
	 */
	AccountEntity findById(UUID accountId);
    /**
     * Finds account by email
     * @param email account email
     * @return account entity
     */
	AccountEntity findByEmail(String email);
	/**
	 * Finds account by email and password
	 * @param email account email
	 * @param password account password
	 * @return account entity
	 */
	AccountEntity findByEmailAndPassword(String email, String password); 
    /**
     * Adds a new account into persistence storage
     * @param account new account entity
     * @return saved account entity
     */
    boolean save(AccountEntity account);
    /**
     * Generated hashed password of the password
     * @param password account password
     * @return hashed password
     */
    String generateHashedPassword(String password);
}
