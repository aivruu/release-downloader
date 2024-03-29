/*
 * This file is part of release-downloader - https://github.com/aivruu/release-downloader
 * Copyright (C) 2020-2024 Aivruu (https://github.com/aivruu)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package me.qeklydev.downloader.http;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import me.qeklydev.downloader.codec.DeserializationUtils;
import me.qeklydev.downloader.release.ReleaseModel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public record HTTPReleaseModelRequest(@NotNull HttpClient httpClient, @NotNull String repository) implements HTTPModelRequest<ReleaseModel> {
  @Override
  public @NotNull CompletableFuture<@Nullable ReleaseModel> provideModel() {
    return this.executeGETRequest().thenApply(json ->
        (json == null) ? null : DeserializationUtils.withReleaseCodec(json));
  }

  @Override
  public @NotNull CompletableFuture<@Nullable String> executeGETRequest() {
    return CompletableFuture.supplyAsync(() -> {
      try {
        final var request = HttpRequest.newBuilder()
            .GET()
            .uri(new URI(this.repository + "/releases/latest"))
            .timeout(TIME_OUT)
            .build();
        final var response = this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        final var responseStatusCode = response.statusCode();
        /*
         * We need to check if the requested repository exists
         * to could request latest release information.
         * In this case the HTTP request must respond with a 404
         * status code.
         */
        return (responseStatusCode == 404) ? null : response.body();
      } catch (final Exception exception) {
        exception.printStackTrace();
        return null;
      }
    });
  }
}
