clear all; clc; format compact; format short g;
%parameter
R=2700;
C=10e-9;
dt=1e-1
f_0=10;
f_E=1e6;
N=1e6;
lw=1;
%Funktionen
g=@(f)1./(1+2*pi*f*j*C*R);
% daten
f_data=linspace(f_0,f_E,N);
g_data=g(f_data);
%PLOT
figure(1);
subplot(2,1,1);
semilogx(f_data,20*log(abs(g_data))/log(10),'linewidth',lw);
xlabel('f[Hz]');ylabel('|G|[dB]');title('Amplitudengang |G(f)|');
grid on;
subplot(2,1,2);
semilogx(f_data,angle(g_data)/pi,'linewidth',lw);
xlabel('f[Hz]');ylabel('\phi[\pi]');title('Phasengang in rad');
grid on;