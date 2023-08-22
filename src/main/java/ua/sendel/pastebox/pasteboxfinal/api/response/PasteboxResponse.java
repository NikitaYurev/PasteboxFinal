package ua.sendel.pastebox.pasteboxfinal.api.response;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PasteboxResponse {
    private final String data;
    private final boolean isPublic;
}
