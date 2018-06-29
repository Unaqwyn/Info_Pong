clear all; clc; format compact; format short g;
%parameter
R1=820;
R2=2700;
C1=2.2e-6;
C2=10e-9;
dt=1e-1;
f_0=10;
f_E=1e6;
N=1e6;
lw=3;
%Funktionen
g=@(f)(-R2./((1+(2*pi*f*R2*C2).^2).^0.5)).*(2*pi*f*C1/((1+(2*pi*f*R1*C1).^2).^0.5));
% daten
f_data=linspace(f_0,f_E,N);
g_data=g(f_data);
%exp data
f_exp_Data=[10,30,100,300,1000,3000,10000,30000,100000,300000];
third_u_Data_1V=[0.2,0.5,1,1.5,1.6,1.5,0.9,0.4,0.1,0.05];
third_phi_Data_1V=[1/2,1/2,2/5,0.46,0.48,0.58,0.32,0.72,1/4,0.19];
%PLOT
figure(4);
subplot(2,1,1);
semilogx(f_data,20*log(abs(g_data))/log(10),'linewidth',lw);
hold on;
semilogx(f_exp_Data,20*log(third_u_Data_1V*2)/log(10),'linewidth',lw);
hold off;
xlabel('f[Hz]');ylabel('|G|[dB]');title('Amplitudengang |G(f)|');
subplot(2,1,2);
semilogx(f_data,angle(g_data)/pi,'linewidth',lw);
hold on;
semilogx(f_exp_Data,-1*third_phi_Data_1V,'linewidth',lw);
hold off;
xlabel('f[Hz]');
ylabel('\phi[\pi]');
title('Phasengang in rad');

grid on;