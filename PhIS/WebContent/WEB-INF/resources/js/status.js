/**
 * Created by ilinca on 18/11/2016.
 */


$(document).ready(function() {
    var status = "";
    console.log("Document ready.");

    var statusCheck = setInterval(function () {
        if (jobId) {
            $.getJSON(baseUrl + "/rest/upload/status?jobId=" + jobId, function (data) {
                status = data;
                console.log(data);
            })
            .complete(function () {
                if (status != null) {
                    var liCount = $("#statusUpdatesUl").children().length / 3;
                    if (liCount < status.jobUpdates.length) {
                        status.jobUpdates.forEach(function myFunction(item, index, arr) {
                            if (index >= liCount) {
                                $("#statusUpdatesUl").append("<div class='left'>" + "<svg xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\">  <line class=\"status success-" + item.success + "\" x1=\"10\"  y1=\"0\" x2=\"10\" y2=\"50\"/> <circle class=\"status success-" + item.success + "\" cx=\"10\" cy=\"53\" r=\"5\"/></svg></div>");
                                $("#statusUpdatesUl").append("<div class='left' <span class=\"label label-default\">" + item.message + "</span></div>");
                                $("#statusUpdatesUl").append("<div class=\"clear\"> </div>");
                            }
                        });
                    }
                    if (status.completed) {
                        clearInterval(statusCheck); // stop polling
                        if (status.success) {
                            $("#statusUpdatesUl").append("<br/><p> Completed: validation successfully completed. </p>");
                        } else {
                            $("#statusUpdatesUl").append("<br/><p> Completed: validation failed. Please fix the mentioned issues and submit again. </p>");
                        }
                    }
                }
            });
        }
    }, 2000);

})


