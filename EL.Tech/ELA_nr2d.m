clear all; clc; format compact; format short g;
%parameter
R=820;
C1=2.2e-6;
C2=47e-9;
dt=1e-1;
f_0=10;
f_E=1e6;
N=1e6;
lw=3;
%Funktionen
g=@(f)(C1/(C1+C2)).*(1i*2*pi*f*R*(C1+C2))./(1+1i*2*pi*f*R*(C1+C2));
% daten
f_data=linspace(f_0,f_E,N);
g_data=g(f_data);
%PLOT
figure(4);
subplot(2,1,1);
semilogx(f_data,20*log(abs(g_data))/log(10),'linewidth',lw);
xlabel('f[Hz]');ylabel('|G|[dB]');title('Amplitudengang |G(f)|')

subplot(2,1,2);
semilogx(f_data,angle(g_data)/pi,'linewidth',lw);
xlabel('f[Hz]');
ylabel('Argument von G(f)');
title('Phasengang in rad');
grid on;