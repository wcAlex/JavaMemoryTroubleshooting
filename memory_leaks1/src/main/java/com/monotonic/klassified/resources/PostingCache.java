package com.monotonic.klassified.resources;

import com.monotonic.klassified.api.Posting;
import com.monotonic.klassified.jdbi.Advert;

import java.util.HashMap;
import java.util.Map;

public class PostingCache
{
    private final Map<PostingId, Posting> idToPosting = new HashMap<>();

    public Posting get(final Advert advert)
    {
        final PostingId postingId = new PostingId(advert.getId());
        return idToPosting.computeIfAbsent(postingId, key ->
            new Posting(
                advert.getId(),
                advert.getCategory(),
                advert.getTitle(),
                advert.getDescription(),
                advert.getPostedAt().toEpochSecond(),
                advert.getLastModifiedAt().toEpochSecond(),
                advert.getPostedBy().getName(),
                advert.getPostedBy().getContactDetails()));
    }

    private class PostingId
    {
        private final long id;

        private PostingId(final long id)
        {
            this.id = id;
        }

        public int hashCode()
        {
            return Long.hashCode(id);
        }

        // missing equal function makes cache key match always return false.
//        public boolean equals(final Object o) {
//            if (this == o) {
//                return true;
//            }
//
//            if (o == null || getClass() != o.getClass()) {
//                return false;
//            }
//
//            final PostingId postingId = (PostingId) o;
//
//            return id == postingId.id;
//        }
    }

}
