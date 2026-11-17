import { Injectable } from '@angular/core';
import Keycloak from 'keycloak-js';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class AuthService {
  readonly keycloak = new Keycloak({
    url: environment.keycloak.url,
    realm: environment.keycloak.realm,
    clientId: environment.keycloak.clientId,
  });

  async init(): Promise<boolean> {
    const authenticated = await this.keycloak.init({
      onLoad: 'login-required',
      pkceMethod: 'S256',
      checkLoginIframe: false,
    });
    if (authenticated) {
      setInterval(() => {
        this.keycloak.updateToken(30).catch(() => this.keycloak.login());
      }, 15000);
    }
    return authenticated;
  }

  get token(): string | undefined {
    return this.keycloak.token;
  }

  get username(): string | undefined {
    return this.keycloak.tokenParsed?.['preferred_username'];
  }

  get fullName(): string | undefined {
    const claims = this.keycloak.tokenParsed;
    return (
      claims?.['name'] ||
      [claims?.['given_name'], claims?.['family_name']].filter(Boolean).join(' ') ||
      this.username
    );
  }

  get roles(): string[] {
    return this.keycloak.realmAccess?.roles ?? [];
  }

  hasRole(role: string): boolean {
    return this.roles.includes(role);
  }

  logout(): void {
    this.keycloak.logout({ redirectUri: window.location.origin });
  }
}
