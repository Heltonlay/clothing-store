package com.daniel.clothing_store.exceptions;

import java.net.URI;
import java.time.Instant;

public record ExceptionBody(Instant moment, String error, String details, Integer status, String path) {
}
