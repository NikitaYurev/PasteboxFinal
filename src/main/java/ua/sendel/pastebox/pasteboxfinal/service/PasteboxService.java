package ua.sendel.pastebox.pasteboxfinal.service;

import ua.sendel.pastebox.pasteboxfinal.api.request.PasteboxRequest;
import ua.sendel.pastebox.pasteboxfinal.api.response.PasteboxResponse;
import ua.sendel.pastebox.pasteboxfinal.api.response.PasteboxUrlResponse;

import java.util.List;

public interface PasteboxService {
    PasteboxResponse getByHash(String hash);
    List<PasteboxResponse> getFirstPublicPasteboxes();
    PasteboxUrlResponse create(PasteboxRequest request);
}

