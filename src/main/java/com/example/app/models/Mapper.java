package com.example.app.models;

public interface Mapper {

    /**
     * Get the id of the latest model.
     * @return id of the latest model, 0 if there is no model
     */
    Long getLatestId();

    /**
     * Get a model with id.
     * @param id id of the target model
     * @return required model
     */
    Model getById(long id);

    /**
     * Insert a model.
     * @param model The model to be inserted
     * @return id
     */
    long insert(Model model);

}
