package net.wojteksz128.authservice.clientapp;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Component
class ClientAppRepositoryInMemoryImpl implements ClientAppRepository {

    private final AtomicLong counter = new AtomicLong();
    private final Map<String, ClientApp> clientApps = new HashMap<>();

    @Override
    public Optional<ClientApp> findByGuid(String guid) {
        if (guid == null) {
            return Optional.empty();
        }

        return Optional.ofNullable(clientApps.get(guid));
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
    public Optional<ClientApp> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }

        return clientApps.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .filter(app -> app.getId().equals(id))
                .findFirst();
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
