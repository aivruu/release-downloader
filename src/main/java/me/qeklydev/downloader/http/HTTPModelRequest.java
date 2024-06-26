/*
 * This file is part of release-downloader - https://github.com/aivruu/release-downloader
 * Copyright (C) 2020-2024 aivruu (https://github.com/aivruu)
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

import org.jetbrains.annotations.Nullable;

/**
 * This interface is used for handle HTTP requests triggered
 * using the GitHub API, and provides half-complete information
 * about these repositories/releases.
 *
 * @param <T> The model that will be returned after the
 * @since 0.0.1
 */
public interface HTTPModelRequest<T> {
  /**
   * Executes the request to the API for receive a json
   * body with the repository, or release information, this
   * data will be serialized using the specific codec type,
   * and will return the built needed model.
   *
   * @return The model needed for this request type.
   *     Otherwise {@code null}.
   * @since 0.0.1
   * @see HTTPModelRequest#executeGETRequest()
   */
  @Nullable T provideModel();

  /**
   * Executes a GET type request to the API with a specific
   * time-out, the response can be a Json text. Or {@code null}
   * if the repository doesn't exist, or an exception was triggered
   * during the operation.
   *
   * @return A possible {@code null} {@link String} that is the JSON-body
   *     response for this request.
   * @since 0.0.1
   */
  @Nullable String executeGETRequest();
}
