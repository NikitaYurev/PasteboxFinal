package ua.sendel.pastebox.pasteboxfinal.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import ua.sendel.pastebox.pasteboxfinal.api.request.PasteboxRequest;
import ua.sendel.pastebox.pasteboxfinal.api.request.PublicStatus;
import ua.sendel.pastebox.pasteboxfinal.api.response.PasteboxResponse;
import ua.sendel.pastebox.pasteboxfinal.api.response.PasteboxUrlResponse;
import ua.sendel.pastebox.pasteboxfinal.repository.PasteBoxEntity;
import ua.sendel.pastebox.pasteboxfinal.repository.PasteboxRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Setter
@ConfigurationProperties(prefix = "app")
public class PasteboxServiceImpl implements PasteboxService {

    private String host;
    private int publicListSize;

    private final PasteboxRepository repository;
    private AtomicInteger idGenerator = new AtomicInteger(0);
    @Override
    public PasteboxResponse getByHash(String hash) {
        PasteBoxEntity pasteBoxEntity = repository.getByHash(hash);
        return new PasteboxResponse(pasteBoxEntity.getData(), pasteBoxEntity.isPublic());
    }

    @Override
    public List<PasteboxResponse> getFirstPublicPasteboxes() {

        List<PasteBoxEntity> list = repository.getListOfPublicAndAlive(publicListSize);

        return list.stream().map(pasteBoxEntity ->
                    new PasteboxResponse(pasteBoxEntity.getData(), pasteBoxEntity.isPublic()))
                .collect(Collectors.toList());
    }

    @Override
    public PasteboxUrlResponse create(PasteboxRequest request) {
        int hash = generateId();
        PasteBoxEntity pasteBoxEntity = new PasteBoxEntity();
        pasteBoxEntity.setData(request.getData());
        pasteBoxEntity.setId(hash);
        pasteBoxEntity.setHash(Integer.toHexString(hash));
        pasteBoxEntity.setPublic(request.getPublicStatus() == PublicStatus.PUBLIC);
        pasteBoxEntity.setLifetime(LocalDateTime.now().plusSeconds(request.getExpirationTimeSeconds()));
        repository.add(pasteBoxEntity);

        return new PasteboxUrlResponse(host + "/" + pasteBoxEntity.getHash());
    }

    private int generateId() {
        return idGenerator.getAndIncrement();
    }
}
