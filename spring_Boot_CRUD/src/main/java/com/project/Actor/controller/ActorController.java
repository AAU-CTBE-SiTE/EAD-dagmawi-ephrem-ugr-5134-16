
package com.project.Actor.controller;

import com.project.Actor.model.Actor;

import com.project.Actor.services.ActorService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;


@RestController
@RequestMapping("/api/actors")
@RequiredArgsConstructor
public class ActorController {

    private final ActorService actorService;

    // Create (POST)
    @PostMapping
    public ResponseEntity<Actor> createActor(@RequestBody Actor actor) {
         Actor savedActor = actorService.createActor(actor);
        return new ResponseEntity<>(savedActor, HttpStatus.CREATED);
    }

    //  Read all (GET)
    @GetMapping
    public ResponseEntity<List<Actor>> getAllActors() {
          return ResponseEntity.ok(actorService.getAllActors());
    }

    //  Read by ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<Actor> getActorById(@PathVariable Long id) {
        return actorService.getActorById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    //  Full update (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Actor> updateActor(@PathVariable Long id, @RequestBody Actor updatedActor) {
        return actorService.updateActor(id, updatedActor)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    //  Partial update (PATCH)
    @PatchMapping("/{id}")
    public ResponseEntity<Actor> patchActor(@PathVariable Long id, @RequestBody Actor patchData) {
        return actorService.patchActor(id, patchData)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    //  Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActor(@PathVariable Long id) {
        if (!actorService.deleteActor(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.noContent().build();
    }
}
