package com.tcs.assignmentseven.datamodel;

import android.util.Log;

import java.util.Arrays;
import java.util.List;

public class MapData {
    String[] html_attributions;
    String next_page_token;
    List<Results> results;
    String status;

    @Override
    public String toString() {
        String result = "";
        int i=0;
        for(Results r : results){
            i+=1;
            result = result+"\n"+i+") "+r.getName()+": "+r.getVicinity();
            if(i>4) break;
        }
        return result;
    }

    public String[] getHtml_attributesl() {
        return html_attributions;
    }

    public void setHtml_attributesl(String[] html_attributesl) {
        this.html_attributions = html_attributesl;
    }

    public String getNext_page_token() {
        return next_page_token;
    }

    public void setNext_page_token(String next_page_token) {
        this.next_page_token = next_page_token;
    }

    public List<Results> getResults() {
        return results;
    }

    public void setResultsList(List<Results> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private class Results {
        String business_status;
        Geometry geometry;
        String icon;
        String name;
        OpeningHours opening_hours;
        List<Photo> photos;
        String place_id;
        PlusCode plus_code;
        double rating;
        String reference;
        String scope;
        String[] types;
        double user_ratings_total;
        String vicinity;

        @Override
        public String toString() {
            return "Results{" +
                    "business_status='" + business_status + '\'' +
                    ", geometry=" + geometry +
                    ", icon='" + icon + '\'' +
                    ", name='" + name + '\'' +
                    ", opening_hours=" + opening_hours +
                    ", photos=" + photos +
                    ", place_id='" + place_id + '\'' +
                    ", plus_code=" + plus_code +
                    ", rating=" + rating +
                    ", reference='" + reference + '\'' +
                    ", scope='" + scope + '\'' +
                    ", types=" + Arrays.toString(types) +
                    ", user_ratings_total=" + user_ratings_total +
                    ", vicinity='" + vicinity + '\'' +
                    '}';
        }

        public String getBusiness_status() {
            return business_status;
        }

        public void setBusiness_status(String business_status) {
            this.business_status = business_status;
        }

        public Geometry getGeometry() {
            return geometry;
        }

        public void setGeometry(Geometry geometry) {
            this.geometry = geometry;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public OpeningHours getOpening_hours() {
            return opening_hours;
        }

        public void setOpening_hours(OpeningHours opening_hours) {
            this.opening_hours = opening_hours;
        }

        public List<Photo> getPhotos() {
            return photos;
        }

        public void setPhotos(List<Photo> photos) {
            this.photos = photos;
        }

        public String getPlace_id() {
            return place_id;
        }

        public void setPlace_id(String place_id) {
            this.place_id = place_id;
        }

        public PlusCode getPlus_code() {
            return plus_code;
        }

        public void setPlus_code(PlusCode plus_code) {
            this.plus_code = plus_code;
        }

        public double getRating() {
            return rating;
        }

        public void setRating(double rating) {
            this.rating = rating;
        }

        public String getReference() {
            return reference;
        }

        public void setReference(String reference) {
            this.reference = reference;
        }

        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
        }

        public String[] getTypes() {
            return types;
        }

        public void setTypes(String[] types) {
            this.types = types;
        }

        public double getUser_ratings_total() {
            return user_ratings_total;
        }

        public void setUser_ratings_total(double user_ratings_total) {
            this.user_ratings_total = user_ratings_total;
        }

        public String getVicinity() {
            return vicinity;
        }

        public void setVicinity(String vicinity) {
            this.vicinity = vicinity;
        }
    }
    private class Geometry {
        Location location;
        Viewport viewport;

        @Override
        public String toString() {
            return "Geometry{" +
                    "location=" + location +
                    ", viewport=" + viewport +
                    '}';
        }

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public Viewport getViewport() {
            return viewport;
        }

        public void setViewport(Viewport viewport) {
            this.viewport = viewport;
        }
    }
    private class Location{
        double lat;
        double lng;

        @Override
        public String toString() {
            return "Location{" +
                    "lat=" + lat +
                    ", lng=" + lng +
                    '}';
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }
    }
    private class Viewport {
        Location northeast;
        Location southwest;

        @Override
        public String toString() {
            return "Viewport{" +
                    "northeast=" + northeast +
                    ", southwest=" + southwest +
                    '}';
        }

        public Location getNortheast() {
            return northeast;
        }

        public void setNortheast(Location northeast) {
            this.northeast = northeast;
        }

        public Location getSouthwest() {
            return southwest;
        }

        public void setSouthwest(Location southwest) {
            this.southwest = southwest;
        }
    }

    private class OpeningHours {
        boolean open_now;

        @Override
        public String toString() {
            return "OpeningHours{" +
                    "open_now=" + open_now +
                    '}';
        }

        public boolean getOpen_now() {
            return open_now;
        }

        public void setOpen_now(boolean open_now) {
            this.open_now = open_now;
        }
    }
    private class Photo{
        int height;
        String[] html_attributions;
        String photo_reference;
        int width;
        @Override
        public String toString() {
            return "Photo{" +
                    "height=" + height +
                    ", width=" + width +
                    ", html_attributes=" + Arrays.toString(html_attributions) +
                    ", photo_reference='" + photo_reference + '\'' +
                    '}';
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public String[] getHtml_attributes() {
            return html_attributions;
        }

        public void setHtml_attributes(String[] html_attributes) {
            this.html_attributions = html_attributes;
        }

        public String getPhoto_reference() {
            return photo_reference;
        }

        public void setPhoto_reference(String photo_reference) {
            this.photo_reference = photo_reference;
        }
    }

    private class PlusCode {
        String compound_code;
        String global_code;

        @Override
        public String toString() {
            return "PlusCode{" +
                    "compound_code='" + compound_code + '\'' +
                    ", global_code='" + global_code + '\'' +
                    '}';
        }

        public String getCompound_code() {
            return compound_code;
        }

        public void setCompound_code(String compound_code) {
            this.compound_code = compound_code;
        }

        public String getGlobal_code() {
            return global_code;
        }

        public void setGlobal_code(String global_code) {
            this.global_code = global_code;
        }
    }
}
