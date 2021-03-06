package cz.csfd.csfdroid.data;

import android.text.TextUtils;
import android.util.Base64;
import cz.csfd.csfdroid.data.CsfdDataProvider.Enum_TimeRange;
import cz.csfd.csfdroid.data.CsfdDataProvider.Enum_OrderBy;
import cz.csfd.csfdroid.data.entity.User.Section;
import cz.csfd.csfdroid.module.home.C2284d.Enum_ReleaseType;
import cz.csfd.csfdroid.p061d.C1998s;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

/* compiled from: CsfdApiUrlHelper */
// C2018b
public class CsfdApiUrlHelper {
    public String url_homeData(List<String> list) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CsfdApiUrlHelper.getBaseApiUrl()).append("/").append("home?data=");
        for (String append : list) {
            stringBuilder.append(append).append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append("&limit=3");
        stringBuilder.append("&creator_profile_visits_limit=4");
        return stringBuilder.toString();
    }

    public static String getBaseApiUrl() {
        return "https://android-api.csfd.cz";
    }

    public String url_identity() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CsfdApiUrlHelper.getBaseApiUrl()).append("/").append(HTTP.IDENTITY_CODING);
        return stringBuilder.toString();
    }

    public String url_filmsFromCreatorId(int i) {
        StringBuilder p = url_creatorFromIdBuilder(i);
        p.append("/").append("films").append("?return=array");
        return p.toString();
    }

    public String url_creatorFromId(int i) {
        return url_creatorFromIdBuilder(i).toString();
    }

    private StringBuilder url_creatorFromIdBuilder(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CsfdApiUrlHelper.getBaseApiUrl()).append("/").append("creator").append("/").append(i);
        return stringBuilder;
    }

    public String url_videosFromCreatorIdWithOffsetAndLimit(int creatorId, int offset, int limit) {
        StringBuilder p = url_creatorFromIdBuilder(creatorId);
        p.append("/").append("videos").append("?").append("offset=").append(offset).append("&").append("limit=").append(limit);
        return p.toString();
    }

    public String url_photosFromCreatorIdWithOffsetAndLimit(int creatorId, int offset, int limit) {
        StringBuilder p = url_creatorFromIdBuilder(creatorId);
        p.append("/").append("photos").append("?").append("offset=").append(offset).append("&").append("limit=").append(limit);
        return p.toString();
    }

    public String url_photosFromFilmIdWithOffsetAndLimitAndWidth(int filmId, int offset, int limit, int width) {
        StringBuilder q = url_filmFromIdBuilder(filmId);
        q.append("/").append("photos").append("?").append("offset=").append(offset).append("&").append("limit=").append(limit).append("&").append("size=all").append("&").append("width=").append(width);
        return q.toString();
    }

    public String url_videosFromFilmIdWithOffsetAndLimitAndWidth(int filmId, int offset, int limit, int width) {
        StringBuilder q = url_filmFromIdBuilder(filmId);
        q.append("/").append("videos").append("?").append("offset=").append(offset).append("&").append("limit=").append(limit).append("&").append("width=").append(width);
        return q.toString();
    }

    public String url_commentsFromFilmIdWithOffsetAndLimit(int filmId, int offset, int limit) {
        StringBuilder q = url_filmFromIdBuilder(filmId);
        q.append("/").append("comments").append("?").append("offset=").append(offset).append("&").append("limit=").append(limit);
        return q.toString();
    }

    public String url_triviaFromFilmIdWithOffsetAndLimit(int filmId, int offset, int limit) {
        StringBuilder q = url_filmFromIdBuilder(filmId);
        q.append("/").append("trivia").append("?").append("offset=").append(offset).append("&").append("limit=").append(limit);
        return q.toString();
    }

    public String url_creatorsFromFilmId(int i) {
        StringBuilder q = url_filmFromIdBuilder(i);
        q.append("/").append("creators");
        return q.toString();
    }

    public String url_myRatingFromFilmId(int i) {
        StringBuilder q = url_filmFromIdBuilder(i);
        q.append("/").append("my-rating");
        return q.toString();
    }

    public String url_myCommentFromFilmId(int i) {
        StringBuilder q = url_filmFromIdBuilder(i);
        q.append("/").append("my-comment");
        return q.toString();
    }

    public String url_filmFromId(int i) {
        return url_filmFromIdBuilder(i).toString();
    }

    private StringBuilder url_filmFromIdBuilder(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CsfdApiUrlHelper.getBaseApiUrl()).append("/").append("film").append("/").append(i);
        return stringBuilder;
    }

    public String url_searchWithQuery(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append(CsfdApiUrlHelper.getBaseApiUrl()).append("/").append("search?q=").append(URLEncoder.encode(str, HTTP.UTF_8));
        } catch (Exception e) {
            C1998s.m6371a(getClass(), e);
        }
        return stringBuilder.toString();
    }

    public String url_searchUsersWithQueryAndLimit(String str, int i) {
        String str2;
        Exception e;
        try {
            str2 = CsfdApiUrlHelper.getBaseApiUrl() + "/search/users/?q=" + URLEncoder.encode(str, HTTP.UTF_8);
            if (i > 0) {
                try {
                    str2 = str2 + "&limit=" + i;
                } catch (UnsupportedEncodingException e2) {
                    e = e2;
                    C1998s.m6371a(getClass(), e);
                    return str2;
                }
            }
        } catch (Exception e3) {
            Exception exception = e3;
            str2 = null;
            e = exception;
            C1998s.m6371a(getClass(), e);
            return str2;
        }
        return str2;
    }

    public String url_cinemaFromNearestWithFilmIdAndOnlyWithScheduleAndTimeRange(double d, double d2, int i, boolean z, Enum_TimeRange c2019c) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CsfdApiUrlHelper.getBaseApiUrl()).append("/").append("cinema").append("/").append("nearest").append("?").append("latitude=").append(d).append("&").append("longitude=").append(d2);
        if (i > 0) {
            stringBuilder.append("&").append("film_id=").append(i);
        }
        if (z) {
            stringBuilder.append("&").append("only_with_schedule=").append("1");
        }
        if (c2019c != null) {
            stringBuilder.append("&").append("time_range=").append(c2019c.name().toLowerCase());
        }
        return stringBuilder.toString();
    }

    public String url_cinemaFromTownsWithFilmIdAndOnlyWithScheduleAndTimeRange(String str, int i, boolean z, Enum_TimeRange c2019c) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append(CsfdApiUrlHelper.getBaseApiUrl()).append("/").append("cinema").append("/").append("towns").append("?").append("name=").append(URLEncoder.encode(str, HTTP.UTF_8)).append("&with_cinemas=1");
            if (i > 0) {
                stringBuilder.append("&").append("film_id=").append(i);
            }
            if (z) {
                stringBuilder.append("&").append("only_with_schedule=").append("1");
            }
            if (c2019c != null) {
                stringBuilder.append("&").append("time_range=").append(c2019c.name().toLowerCase());
            }
        } catch (Exception e) {
            C1998s.m6371a(getClass(), e);
        }
        return stringBuilder.toString();
    }

    public String url_cinemaFromTowns(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append(CsfdApiUrlHelper.getBaseApiUrl()).append("/").append("cinema").append("/").append("towns").append("?").append("name=").append(URLEncoder.encode(str, HTTP.UTF_8));
        } catch (Exception e) {
            C1998s.m6371a(getClass(), e);
        }
        return stringBuilder.toString();
    }

    public String url_cinemaFromId(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CsfdApiUrlHelper.getBaseApiUrl()).append("/").append("cinema").append("/").append(i);
        return stringBuilder.toString();
    }

    public String url_charts() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CsfdApiUrlHelper.getBaseApiUrl()).append("/").append("charts").append("/");
        return stringBuilder.toString();
    }

    public String url_chartsFromNameWithOffsetAndLimit(String str, int i, int i2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CsfdApiUrlHelper.getBaseApiUrl()).append("/").append("charts").append("/").append(str).append("?").append("offset=").append(i).append("&").append("limit=").append(i2);
        return stringBuilder.toString();
    }

    public String url_userFromId(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CsfdApiUrlHelper.getBaseApiUrl()).append("/").append("user").append("/").append(i);
        return stringBuilder.toString();
    }

    public String url_filmRatingsFromUserIdWithOffsetAndLimit(int i, int i2, int i3) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CsfdApiUrlHelper.getBaseApiUrl()).append("/").append("user").append("/").append(i).append("/").append("film-ratings").append("?").append("offset=").append(i2).append("&").append("limit=").append(i3);
        return stringBuilder.toString();
    }

    public String url_filmCommentsFromsUserIdWithOffsetAndLimit(int i, int i2, int i3) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CsfdApiUrlHelper.getBaseApiUrl()).append("/").append("user").append("/").append(i).append("/").append("film-comments").append("?").append("offset=").append(i2).append("&").append("limit=").append(i3);
        return stringBuilder.toString();
    }

    public String url_sectionFromUserIdAndSectionPath(int i, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        String str2 = "fanclub-" + str.toLowerCase();
        if (str.equals(Section.FAVOURITE_USERS.sectionPath())) {
            str2 = "favorites";
        }
        stringBuilder.append(CsfdApiUrlHelper.getBaseApiUrl()).append("/").append("user").append("/").append(i).append("/").append(str2);
        return stringBuilder.toString();
    }

    public String url_favoritesActivitiyWithOffset(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CsfdApiUrlHelper.getBaseApiUrl()).append("/").append("favorites").append("/").append("activity").append("/?").append("types").append("=film_rating%2Cfilm_comment").append("&").append("limit").append("=").append(20).append("&").append("offset").append("=").append(i);
        return stringBuilder.toString();
    }

    public String url_watchlistWithOffset(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CsfdApiUrlHelper.getBaseApiUrl()).append("/").append("watchlist").append("/?").append("limit").append("=").append(20).append("&").append("offset").append("=").append(i);
        return stringBuilder.toString();
    }

    public String url_watchlist() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CsfdApiUrlHelper.getBaseApiUrl()).append("/").append("watchlist");
        return stringBuilder.toString();
    }

    // probably used to PUT or DELETE films from watchlist
    public String url_watchlistWithFilmId(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CsfdApiUrlHelper.getBaseApiUrl()).append("/").append("watchlist").append("/?").append("film_id").append("=").append(i);
        return stringBuilder.toString();
    }

    public String url_tvTipsWithOffsetAndDateTimeStampWithOrder(long j, int i, Enum_OrderBy c2020d) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CsfdApiUrlHelper.getBaseApiUrl()).append("/").append("tv/tips").append("/?").append("limit").append("=").append(20).append("&").append("offset").append("=").append(i).append("&").append("date").append("=").append(String.format(Locale.ENGLISH, "%tY-%<tm-%<td", new Object[]{Long.valueOf(j)})).append("&").append("orderBy").append("=").append(c2020d.m6699a());
        return stringBuilder.toString();
    }

    public String url_tvScheduleWithDateAndStationsIdsListWithOffset(Date date, List<Integer> list, int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CsfdApiUrlHelper.getBaseApiUrl()).append("/").append("tv/schedule").append("/?").append("limit").append("=").append(20).append("&").append("offset").append("=").append(i).append("&").append("date").append("=").append(String.format(Locale.ENGLISH, "%tY-%<tm-%<td", new Object[]{date}));
        if (list != null && list.size() > 0) {
            stringBuilder.append("&").append("stations").append("=").append(TextUtils.join(",", list));
        }
        return stringBuilder.toString();
    }

    public String url_tvStations() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CsfdApiUrlHelper.getBaseApiUrl()).append("/").append("tv/stations");
        return stringBuilder.toString();
    }

    @Deprecated
    public static String getRequestAccessTokenUrl() {
        StringBuilder stringBuilder = new StringBuilder(CsfdApiUrlHelper.getBaseApiUrl());
        stringBuilder.append("/").append("request-access-token").append("?").append("api_consumer_key=").append("061025241049");
        return stringBuilder.toString();
    }

    public String url_adBannerWithWidthAndHeightAndSectionAndParameters(int i, int i2, String str, Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CsfdApiUrlHelper.getBaseApiUrl()).append("/").append("ad/banner").append("?").append("width=").append(i).append("&").append("height=").append(i2).append("&").append("section=").append(str);
        if (map != null && map.size() > 0) {
            stringBuilder.append("&").append("parameters=").append(Base64.encodeToString(new JSONObject(map).toString().getBytes(), 0));
        }
        return stringBuilder.toString();
    }

    public String url_releasesFromReleaseTypeAndDate(Enum_ReleaseType c2283b, Date date) {
        String format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(date);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CsfdApiUrlHelper.getBaseApiUrl()).append("/").append("releases").append("/").append(c2283b.toString().toLowerCase()).append("?date=").append(format);
        return stringBuilder.toString();
    }

    public String url_cinemaFavorites() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CsfdApiUrlHelper.getBaseApiUrl()).append("/").append("cinema").append("/").append("favorites");
        return stringBuilder.toString();
    }

    public String url_cinemaFavoritesWithFilmIdAndOnlyWithScheduleAndTimeRange(int i, boolean z, Enum_TimeRange c2019c) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CsfdApiUrlHelper.getBaseApiUrl()).append("/").append("cinema").append("/").append("favorites");
        if (i > 0) {
            stringBuilder.append("?").append("film_id=").append(i);
        }
        if (z) {
            stringBuilder.append(stringBuilder.indexOf("?") == -1 ? "?" : "&").append("only_with_schedule=").append("1");
        }
        if (c2019c != null) {
            stringBuilder.append(stringBuilder.indexOf("?") == -1 ? "?" : "&").append("time_range=").append(c2019c.name().toLowerCase());
        }
        return stringBuilder.toString();
    }

    // probably used for PUT and DELETE of cinemas from favorites
    public String url_cinemaFavoritesFromCinemaId(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CsfdApiUrlHelper.getBaseApiUrl()).append("/").append("cinema").append("/").append("favorites").append("?").append("cinema_id=").append(i);
        return stringBuilder.toString();
    }

    public String url_videoTypes() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CsfdApiUrlHelper.getBaseApiUrl()).append("/").append("video").append("/").append("types");
        return stringBuilder.toString();
    }

    public String url_videoFromTypeWithOffsetAndLimit(int i, int i2, int i3) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CsfdApiUrlHelper.getBaseApiUrl()).append("/").append("video");
        stringBuilder.append("?");
        if (i > 0) {
            stringBuilder.append("type=").append(i);
            stringBuilder.append("&");
        }
        stringBuilder.append("offset=").append(i2).append("&").append("limit=").append(i3);
        return stringBuilder.toString();
    }

    public String url_adAdMob() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CsfdApiUrlHelper.getBaseApiUrl()).append("/").append("ad").append("/").append("ad-mob");
        return stringBuilder.toString();
    }

    public String url_adSplashWithWidthAndHeight(int i, int i2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CsfdApiUrlHelper.getBaseApiUrl()).append("/").append("ad").append("/").append("splash").append("?").append("width=").append(i).append("height=").append(i2);
        return stringBuilder.toString();
    }

    public String url_adBottomWithWidthAndHeightAndSectionAndParameters(int i, int i2, String str, Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CsfdApiUrlHelper.getBaseApiUrl()).append("/").append("ad").append("/").append("bottom").append("?").append("width=").append(i).append("&").append("height=").append(i2).append("&").append("section=").append(str);
        if (map != null && map.size() > 0) {
            stringBuilder.append("&").append("parameters=").append(Base64.encodeToString(new JSONObject(map).toString().getBytes(), 0));
        }
        return stringBuilder.toString();
    }

    public String url_messagesWithLimitAndOffset(int i, int i2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CsfdApiUrlHelper.getBaseApiUrl()).append("/").append("messages").append("?").append("limit=").append(i).append("&").append("offset=").append(i2);
        return stringBuilder.toString();
    }

    public String url_messagesThreadWithUserIdAndLimitAndOffset(int i, int i2, int i3) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CsfdApiUrlHelper.getBaseApiUrl()).append("/").append("messages").append("/").append("thread").append("?").append("user_id=").append(i).append("&").append("limit=").append(i2).append("&").append("offset=").append(i3);
        return stringBuilder.toString();
    }

    public String url_messagesDeleteWithUserIdList(List<Integer> list) {
        return CsfdApiUrlHelper.getBaseApiUrl() + "/messages/delete/?user_id=" + joinListWithSeparator((List) list, ",");
    }

    public String url_messagesDeleteWithMessageIdList(List<String> list) {
        return CsfdApiUrlHelper.getBaseApiUrl() + "/messages/delete/?message_id=" + joinListWithSeparator((List) list, ",");
    }

    public String url_messagesUnreadThreads() {
        return CsfdApiUrlHelper.getBaseApiUrl() + "/messages/unread-threads";
    }

    public String url_userIdFavoritesAdd(int i) {
        return CsfdApiUrlHelper.getBaseApiUrl() + "/" + "user" + "/" + i + "/" + "favorites-add";
    }

    public String url_userIdFavoritesRemove(int i) {
        return CsfdApiUrlHelper.getBaseApiUrl() + "/" + "user" + "/" + i + "/" + "favorites-remove";
    }

    public String url_filmEpisodesFromFilmId(int i) {
        return CsfdApiUrlHelper.getBaseApiUrl() + "/film/" + i + "/episodes";
    }

    private String joinListWithSeparator(List list, String str) {
        String str2 = "";
        for (Object next : list) {
            if (!TextUtils.isEmpty(str2)) {
                str2 = str2 + ",";
            }
            str2 = str2 + next;
        }
        return str2;
    }

    public String url_messagesSend() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CsfdApiUrlHelper.getBaseApiUrl()).append("/").append("messages").append("/").append("send");
        return stringBuilder.toString();
    }

    public String url_messagesContacts() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CsfdApiUrlHelper.getBaseApiUrl()).append("/").append("messages").append("/").append("contacts");
        return stringBuilder.toString();
    }
}
