package org.niit.BEJ_C2_S3_REST_API_MongoDB_MC_1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT,reason = "Artist Not Found")
public class TrackNotFoundException extends Exception{

}
