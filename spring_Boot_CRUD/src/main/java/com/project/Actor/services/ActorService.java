package com.project.Actor.services;

import com.project.Actor.model.Actor;
import com.project.Actor.repository.ActorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ActorService {

    private final ActorRepository actorRepository;

    // Create actor
    public Actor createActor(Actor actor) {
        return actorRepository.save(actor);
    }

    // Read actor
    @Transactional(readOnly = true)
    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    // Read by ID
    @Transactional(readOnly = true)
    public Optional<Actor> getActorById(Long id) {
        return actorRepository.findById(id);
    }

    // full update (PUT)
    public Optional<Actor> updateActor(Long id, Actor updatedActor) {
        return actorRepository.findById(id).map(existing -> {
            existing.setMovieName(updatedActor.getMovieName());
            existing.setActorName(updatedActor.getActorName());
            return actorRepository.save(existing);
        });
    }

    // Partial update (PATCH)
    public Optional<Actor> patchActor(Long id, Actor patchData) {
        return actorRepository.findById(id).map(existing -> {
            if (patchData.getMovieName() != null) {
                existing.setMovieName(patchData.getMovieName());
            }
            if (patchData.getActorName() != null) {
                existing.setActorName(patchData.getActorName());
            }
            return actorRepository.save(existing);
        });
    }

    // Delete actor
    public boolean deleteActor(Long id) {
        if (!actorRepository.existsById(id)) {
            return false;
        }
        actorRepository.deleteById(id);
        return true;
    }
}