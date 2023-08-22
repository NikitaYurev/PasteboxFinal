package ua.sendel.pastebox.pasteboxfinal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.sendel.pastebox.pasteboxfinal.api.request.PasteboxRequest;
import ua.sendel.pastebox.pasteboxfinal.api.response.PasteboxResponse;
import ua.sendel.pastebox.pasteboxfinal.api.response.PasteboxUrlResponse;
import ua.sendel.pastebox.pasteboxfinal.service.PasteboxService;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequiredArgsConstructor
public class PasteboxController {
    private final PasteboxService pasteboxService;

    @GetMapping("/")
    public Collection<PasteboxResponse> getPublicPasteList() {
        return pasteboxService.getFirstPublicPasteboxes();
    }

    @GetMapping("/{hash}")
    public PasteboxResponse getByHash(@PathVariable String hash) {
        return pasteboxService.getByHash(hash);
    }

    @PostMapping("/")
    public PasteboxUrlResponse add(@RequestBody PasteboxRequest request){
        return pasteboxService.create(request);
    }
}
