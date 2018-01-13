package net.wojteksz128.authservice.clientapp;

import net.wojteksz128.authservice.exception.InvalidRequestException;
import net.wojteksz128.authservice.exception.ObjectNotFoundException;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Component
class ClientAppRepositoryInMemoryImpl implements ClientAppRepository {

    private final AtomicLong counter = new AtomicLong();
    private final Map<String, ClientApp> clientApps = new HashMap<>();

    @Override
    public ClientApp findByGuid(String guid) throws InvalidRequestException, ObjectNotFoundException {
        if (guid == null) {
            throw new InvalidRequestException("App guid is null.");
        }

        return Optional.ofNullable(clientApps.get(guid))
                .orElseThrow(() -> new ObjectNotFoundException(String.format("App with guid %s not found.", guid)));
    }

    @Override
    public List<ClientApp> findByNameContains(String value) {
        if (value == null) {
            return Collections.emptyList();
        }

        return clientApps.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .filter(app -> app.getName()
                        .contains(value))
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientApp> findAllApps() {
        return clientApps.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientApp> findByUserId(Long userId) {
        return clientApps.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .filter(v -> v.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public ClientApp findById(Long id) throws InvalidRequestException, ObjectNotFoundException {
        if (id == null) {
            throw new InvalidRequestException("App id is null.");
        }

        return clientApps.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .filter(app -> app.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ObjectNotFoundException(String.format("App with id %d not found.", id)));
    }

    @Override
    public ClientApp save(ClientApp app) {
        app.setId(counter.incrementAndGet());
        app.setGuid(UUID.randomUUID().toString());
        clientApps.put(app.getGuid(), app);
        return app;
    }

    @Override
    public void update(ClientApp app) {
        clientApps.replace(app.getGuid(), app);
    }

    @Override
    public void delete(ClientApp app) {
        clientApps.remove(app.getGuid());
    }
}
