import { jwtDecode } from "jwt-decode";

export interface DecodedToken {
  sub: string;
  iss: string;
  jti: string;
  iat: number;
  exp: number;
}

export const getDecodedToken = (token: string ): DecodedToken => {
  return jwtDecode(token);
};