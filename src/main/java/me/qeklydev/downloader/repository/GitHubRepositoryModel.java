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
package me.qeklydev.downloader.repository;

import java.util.List;
import me.qeklydev.downloader.license.RepositoryLicense;
import me.qeklydev.downloader.release.ReleaseModel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * This record represents a GitHub repository model based-on
 * the information provided by the HTTP request to the API.
 *
 * @param owner the owner of the repository.
 * @param name the name of the repository.
 * @param description the description of the repository.
 * @param license the license type of the repository,
 *                {@code null} if any license was provided.
 * @param releaseModel the abstract model for the latest
 *                     repository release, {@code null}
 *                     if there's not any release.
 * @param isForked if the repository is a fork.
 * @param parent the original owner of this repository
 *               if the repository is not a fork, is {@code null}.
 * @param canBeForked if the repository can be forked.
 * @param stars the amount of stars the repository has.
 * @param forks the amount of forks the repository has.
 * @param isPublic if the repository is public.
 * @param isArchived if the repository is archived.
 * @param isDisabled if the repository is disabled.
 * @param language the most used language on this
 *                 repository, {@code null} if there's
 *                 not any language.
 * @param topics the topics of the repository, {@code null}
 *               if there's no topics.
 * @since 0.0.1
 */
public record GitHubRepositoryModel(
    @NotNull String owner, @NotNull String name, @NotNull String description, @Nullable RepositoryLicense license,
    @Nullable ReleaseModel releaseModel, boolean isForked, @Nullable String parent, boolean canBeForked, int stars, int forks, boolean isPublic,
    boolean isArchived, boolean isDisabled, @Nullable String language, @Nullable List<@NotNull String> topics) {
}
