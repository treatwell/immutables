/*
 * Copyright 2019 Immutables Authors and Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.immutables.criteria.reactor;

import org.immutables.criteria.backend.Backend;
import org.immutables.criteria.expression.Query;
import org.immutables.criteria.repository.reactive.ReactiveMapper1;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

public class ReactorMapper1<T1> implements ReactorFetcher<T1> {

  private final ReactiveMapper1<T1> mapper;
  private final ReactorFetcher<T1> fetcher;

  ReactorMapper1(Query query, Backend.Session session) {
    this.mapper = new ReactiveMapper1<>(query, session);
    this.fetcher = ReactorFetcherDelegate.fromReactive(mapper);
  }

  public ReactorFetcher<Optional<T1>> asOptional() {
    return ReactorFetcherDelegate.fromReactive(mapper.asOptional());
  }

  @Override
  public Flux<T1> fetch() {
    return fetcher.fetch();
  }

  @Override
  public Mono<T1> one() {
    return fetcher.one();
  }

  @Override
  public Mono<T1> oneOrNone() {
    return fetcher.oneOrNone();
  }

  @Override
  public Mono<Boolean> exists() {
    return fetcher.exists();
  }

  @Override
  public Mono<Long> count() {
    return fetcher.count();
  }
}
