/*
 * User Responsible: Hasbiya Yusuf
 * Course: CST8288
 * Maker Space Final Project
 *
 * Description:
 * This interface defines the generic CRUD contract for DAO classes.
 * It provides common database operations used by entity-specific DAO
 * interfaces in the application.
 */

package DAO;

import java.sql.SQLException;
import java.util.List;

/**
 * Defines generic CRUD operations for database access.
 *
 * @author Hasbiya Yusuf
 * @version 1.0
 */
public interface GenericDAO<T> {

    /**
     * Inserts a new entity into the database.
     *
     * @param entity entity to create
     * @throws SQLException database error
     */
    void create(T entity) throws SQLException;

    /**
     * Retrieves an entity by its primary key.
     *
     * @param id entity ID
     * @return entity if found, otherwise null
     * @throws SQLException database error
     */
    T findById(int id) throws SQLException;

    /**
     * Updates an existing entity.
     *
     * @param entity entity to update
     * @throws SQLException database error
     */
    void update(T entity) throws SQLException;

    /**
     * Deletes an entity by its primary key.
     *
     * @param id entity ID
     * @throws SQLException database error
     */
    void delete(int id) throws SQLException;

    /**
     * Retrieves all entities.
     *
     * @return list of entities
     * @throws SQLException database error
     */
    List<T> findAll() throws SQLException;
}