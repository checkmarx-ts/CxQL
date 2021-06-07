// Negative
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException, IOException, ServletException {

        if (req.getContentLength() > MAX_REQUEST_SIZE) {
            throw new IOException("request body size too big!");
        }

        Entitlements creds = new ObjectMapper().readValue(req.getInputStream(), Entitlements.class);

        return getAuthenticationManager()
                .authenticate(new UsernamePasswordAuthenticationToken(creds.getId(), "", Collections.emptyList()));
    }

}