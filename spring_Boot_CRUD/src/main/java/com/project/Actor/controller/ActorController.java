
package com.project.Actor.controller;

import com.project.Actor.model.Actor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/actors")
public class ActorController {

    @PersistenceContext
    private EntityManager entityManager;


    //  POST
    @PostMapping
    @Transactional
    public ResponseEntity<Actor> createActor(@RequestBody Actor actor) {

        entityManager.persist(actor);

        return ResponseEntity.ok(actor);
    }


    // READ ALL - GET
    @GetMapping
    public ResponseEntity<List<Actor>> getAllActors() {

        List<Actor> actors = entityManager
                .createQuery("SELECT a FROM Actor a", Actor.class)
                .getResultList();

        return ResponseEntity.ok(actors);
    }


    // READ ONE - GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Actor> getActorById(@PathVariable Long id) {

        Actor actor = entityManager.find(Actor.class, id);

        if (actor == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(actor);
    }


    // UPDATE - PUT
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Actor> updateActor(
            @PathVariable Long id,
            @RequestBody Actor updatedActor) {

        Actor existingActor = entityManager.find(Actor.class, id);

        if (existingActor == null) {
            return ResponseEntity.notFound().build();
        }

        existingActor.setActorName(updatedActor.getActorName());
        existingActor.setMovieName(updatedActor.getMovieName());

        return ResponseEntity.ok(existingActor);
    }


    // PARTIAL UPDATE - PATCH
    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<Actor> patchActor(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {

        Actor existingActor = entityManager.find(Actor.class, id);

        if (existingActor == null) {
            return ResponseEntity.notFound().build();
        }

        if (updates.containsKey("actorName")) {
            existingActor.setActorName(
                    (String) updates.get("actorName"));
        }

        if (updates.containsKey("movieName")) {
            existingActor.setMovieName(
                    (String) updates.get("movieName"));
        }

        return ResponseEntity.ok(existingActor);
    }


    // DELETE - DELETE
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteActor(@PathVariable Long id) {

        Actor actor = entityManager.find(Actor.class, id);

        if (actor == null) {
            return ResponseEntity.notFound().build();
        }

        entityManager.remove(actor);

        return ResponseEntity.noContent().build();
    }
}
